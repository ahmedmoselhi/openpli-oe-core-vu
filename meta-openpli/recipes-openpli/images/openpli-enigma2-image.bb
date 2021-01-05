require openpli-image.bb

WIFI_DRIVERS = " \
	firmware-carl9170 \
	firmware-htc7010 \
	firmware-htc9271 \
	firmware-rt2870 \
	firmware-rt73 \
	firmware-rtl8712u \
	firmware-rtl8192cu \
	firmware-rtl8188eu \
	firmware-zd1211 \
	\
	kernel-module-8192eu \
	kernel-module-ath9k-htc \
	kernel-module-carl9170 \
	kernel-module-r8712u \
	kernel-module-rt2500usb \
	kernel-module-rt2800usb \
	kernel-module-rt73usb \
	kernel-module-rtl8187 \
	kernel-module-rtl8192cu \
	kernel-module-zd1211rw \
	"

WIFI_BSP_DRIVERS ?= " "

OPTIONAL_WIFI_PACKAGES = "\
        ${@ 'kernel-module-8812au' if ("${KERNEL_VERSION}" and "${MACHINE}" != "dm8000" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.0') < 0) else '' } \
        ${@ 'kernel-module-8814au' if ("${KERNEL_VERSION}" and "${MACHINE}" != "dm8000" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.0') < 0) else '' } \
        ${@ 'kernel-module-rt5572sta' if ("${KERNEL_VERSION}" and "${MACHINE}" != "dm8000" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.10') < 0) else '' } \
        ${@ 'kernel-module-rt8188eu' if ("${KERNEL_VERSION}" and "${MACHINE}" != "dm8000" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.12') < 0) else '' } \
        ${@ 'kernel-module-rt3573sta' if ("${KERNEL_VERSION}" and "${MACHINE}" != "dm8000" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.12') < 0) else '' } \
        ${@ 'kernel-module-mt7601usta' if ("${KERNEL_VERSION}" and "${MACHINE}" != "dm8000" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.2') < 0) else '' } \
        ${@ 'kernel-module-8723a' if ("${KERNEL_VERSION}" and "${MACHINE}" != "dm8000" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.5') < 0) else '' } \
        ${@ 'kernel-module-8723bu' if ("${KERNEL_VERSION}" and "${MACHINE}" != "dm8000" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.6') < 0) else '' } \
        ${@ 'kernel-module-8192eu' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.7') < 0) else '' } \
        ${@ 'kernel-module-mt7610u' if ("${KERNEL_VERSION}" and "${MACHINE}" != "dm8000" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.19') < 0) else '' } \
        \
        ${@ 'kernel-module-8192fu' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.0') >= 0) else '' } \
        ${@ 'kernel-module-8821cu' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.0') >= 0) else '' } \
        ${@ 'kernel-module-88xxau' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.0') >= 0) else '' } \
        \
        ${@bb.utils.contains('MACHINE_ESSENTIAL_EXTRA_RDEPENDS', 'rtl8723bs', '', bb.utils.contains('MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS', 'spycat-rtl8723bs', '', 'kernel-mod>
        \
        kernel-module-88x2bu \
        kernel-module-8189es \
        firmware-rtl8723bu \
        firmware-rtl8188eu  \
        firmware-rtl8192eu \
        firmware-mt7601u \
        "

ENIGMA2_PLUGINS = " \
	enigma2-plugin-extensions-audiosync \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-graphmultiepg \
        enigma2-plugin-extensions-kodi \
        enigma2-plugin-skins-mx-hq9w \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-moviecut \
        enigma2-plugin-extensions-openmultiboot \
	enigma2-plugin-extensions-openwebif \
	enigma2-plugin-extensions-pictureplayer \
	enigma2-plugin-extensions-ppanel \
	\
	enigma2-plugin-systemplugins-fastscan \
	enigma2-plugin-systemplugins-hotplug \
	enigma2-plugin-systemplugins-networkbrowser \
	enigma2-plugin-systemplugins-positionersetup \
	enigma2-plugin-systemplugins-satfinder \
        enigma2-plugin-systemplugins-signalfinder \
	enigma2-plugin-systemplugins-softwaremanager \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	enigma2-plugin-systemplugins-osdpositionsetup \
	\
	${@bb.utils.contains("MACHINE_FEATURES", "3dtv", "enigma2-plugin-systemplugins-osd3dsetup" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "hdmicec", "enigma2-plugin-systemplugins-hdmicec" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "wlan", "enigma2-plugin-systemplugins-wirelesslan", "", d)} \
	\
	${@bb.utils.contains('OPENPLI_FEATURES', 'ci', 'enigma2-plugin-systemplugins-commoninterfaceassignment', '', d)} \
	${@bb.utils.contains('OPENPLI_FEATURES', 'dvd', 'enigma2-plugin-extensions-cdinfo enigma2-plugin-extensions-dvdplayer', '', d)} \
	${@bb.utils.contains('OPENPLI_FEATURES', 'fan', 'enigma2-plugin-systemplugins-tempfancontrol', '', d)} \
	${@bb.utils.contains('OPENPLI_FEATURES', '7seg', 'enigma2-plugin-systemplugins-vfdcontrol', '', d)} \
	"

DEPENDS += " \
	enigma2 \
	"

IMAGE_INSTALL += " \
	aio-grab \
	enigma2 \
        kodi \
        kodi-addons-meta \
        kodi-platform \
	libavahi-client \
	ntpdate \
        openmultiboot \
	ofgwrite \
	settings-autorestore \
	tuxbox-common \
	${ENIGMA2_PLUGINS} \
	\
	${WIFI_DRIVERS} \
        ${OPTIONAL_WIFI_PACKAGES} \
	\
	${@bb.utils.contains_any('MACHINE', 'vuuno vuduo vuultimo vusolo vusolo2 vuduo2 vusolose vuzero vuuno4k vuuno4kse vuzero4k vuultimo4k vusolo4k vuduo4k vuduo4kse', 'vuplus-tuner-turbo', '', d)} \
	${@bb.utils.contains_any('MACHINE', 'vuuno4kse vuultimo4k vuduo4k vuduo4kse', 'vuplus-hdmi-in-helper', '', d)} \
	\
	${@bb.utils.contains_any('MACHINE', 'gbquad4k gbue4k', 'kernel-module-88xxau', '', d)} \
	${@bb.utils.contains_any('MACHINE', 'gbquad4k gbue4k', 'enigma2-plugin-systemplugins-wirelesslan', '', d)} \
	\
	${@bb.utils.contains("MACHINE_FEATURES", "chromium", "enigma2-plugin-extensions-chromium", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "transcoding", "enigma2-plugin-systemplugins-transcodingsetup", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "streamproxy", "streamproxy", "", d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'ctrlrc', "enigma2-plugin-systemplugins-remotecontrolcode", "", d)} \
	\
	${@bb.utils.contains('OPENPLI_FEATURES', 'dvd', 'cdtextinfo', '', d)} \
	"

export IMAGE_BASENAME = "openpli-enigma2"
