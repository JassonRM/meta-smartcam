SUMMARY = "Initial boot script for SmartCam"
DESCRIPTION = "Script to start webserver and video pipelines"
LICENSE = "CLOSED"
PR = "r0"

# Runtime dependencies
#
# Add a line similar to the following to ensure any packages needed for the scripts to run are installed in the image.
#
RDEPENDS_{PN} = "detection webserver gstreamer1.0 gstreamer1.0-meta-base gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad gstreamer1.0-plugins-ugly"

# SRC_URI specifies the files that are to be used as the scripts.
#
# Any valid src_uri format can be used - this example assumes the
# scripts are stored in the 'files' directory below the one in
# which this file is located.
#
SRC_URI = "              \
   file://initscript.sh \
   "

# This function is responsible for:
#  1) Ensuring the required directories exist in the image;
#  2) Installing the scripts in to the image;
#  3) Creating the desired runlevel links to the scripts.
#
do_install() {
    #
    # Create directories:
    #   ${D}${sysconfdir}/init.d - will hold the scripts
    #   ${D}${sysconfdir}/rcS.d  - will contain a link to the script that runs at startup
    #   ${D}${sysconfdir}/rc5.d  - will contain a link to the script that runs at runlevel=5
    #   ${D}${sbindir}           - scripts called by the above
    #
    # ${D} is effectively the root directory of the target system.
    # ${D}${sysconfdir} is where system configuration files are to be stored (e.g. /etc).
    # ${D}${sbindir} is where executable files are to be stored (e.g. /sbin).
    #
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rcS.d
    install -d ${D}${sysconfdir}/rc1.d
    install -d ${D}${sysconfdir}/rc2.d
    install -d ${D}${sysconfdir}/rc3.d
    install -d ${D}${sysconfdir}/rc4.d
    install -d ${D}${sysconfdir}/rc5.d
    install -d ${D}${sbindir}

    #
    # Install files in to the image
    #
    # The files fetched via SRC_URI (above) will be in ${WORKDIR}.
    #
    install -m 0755 ${WORKDIR}/initscript.sh      ${D}${sysconfdir}/init.d/

    #
    # Symbolic links can also be installed. e.g.
    #
    # ln -s support-script-link ${D}${sbindir}/support-script

    #
    # Create symbolic links from the runlevel directories to the script files.
    # Links of the form S... and K... mean the script when be called when
    # entering / exiting the runlevel designated by the containing directory.
    # For example:
    #   rc5.d/S90initscript.sh will be called (with %1='start') when entering runlevel 5.
    #   rc5.d/K90initscript.sh will be called (with %1='stop') when exiting runlevel 5.
    #
    ln -sf ../init.d/initscript.sh      ${D}${sysconfdir}/rc1.d/K90initscript.sh
    ln -sf ../init.d/initscript.sh      ${D}${sysconfdir}/rc2.d/K90initscript.sh
    ln -sf ../init.d/initscript.sh      ${D}${sysconfdir}/rc3.d/K90initscript.sh
    ln -sf ../init.d/initscript.sh      ${D}${sysconfdir}/rc4.d/K90initscript.sh
    ln -sf ../init.d/initscript.sh      ${D}${sysconfdir}/rc5.d/S90initscript.sh
}

FILES_${PN} = "/etc /usr"
