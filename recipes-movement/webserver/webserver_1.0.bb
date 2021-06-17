SUMMARY = "RaspberryPi IoT Frontend Server"
DESCRIPTION = "Recipe for a RaspberryPi python webserver"
LICENSE = "CLOSED"
RDEPENDS_{PN} = "python3"

PV = "1.0+git${SRCPV}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI = "git://github.com/kennethGHS/MovementEmbedded.git;branch=main"
SRCREV = "${AUTOREV}"

FILES_${PN} = "/webserver/"

do_install() {
    install -d ${D}/webserver
    cp -r ${WORKDIR}/git/webserver ${D}/
}
