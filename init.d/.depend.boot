TARGETS = console-setup resolvconf mountkernfs.sh ufw apparmor plymouth-log screen-cleanup hostname.sh udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh checkroot.sh lvm2 open-iscsi networking iscsid urandom mountdevsubfs.sh checkfs.sh checkroot-bootclean.sh bootmisc.sh mountall-bootclean.sh mountall.sh mountnfs-bootclean.sh mountnfs.sh kmod procps
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
open-iscsi: networking iscsid
networking: resolvconf mountkernfs.sh urandom procps
iscsid: networking
urandom: hwclock.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountall-bootclean.sh mountnfs-bootclean.sh udev
mountall-bootclean.sh: mountall.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
kmod: checkroot.sh
procps: mountkernfs.sh udev
