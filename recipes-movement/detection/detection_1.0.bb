SUMMARY = "RaspberryPi OpenCV movement detection"
DESCRIPTION = "Recipe for a RaspberryPi SmartCam"
LICENSE = "CLOSED"
DEPENDS = "curl opencv"

PV = "1.0+git${SRCPV}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI = "git://github.com/kennethGHS/MovementEmbedded.git;branch=main"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/MovementAnalysis"

inherit pkgconfig cmake
