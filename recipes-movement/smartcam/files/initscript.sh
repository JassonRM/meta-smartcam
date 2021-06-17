#!/bin/sh

logger "Starting SmartCam"

logger "Starting HTTP server"
cd /webserver
python3 ./server.py &

logger "Starting video capture"
gst-launch-1.0 v4l2src device=/dev/video0 ! image/jpeg ! tee name=split ! queue ! jpegdec ! x264enc speed-preset=ultrafast ! 'video/x-h264,stream-format=byte-stream,profile=baseline' ! mpegtsmux ! hlssink max-files=5 target-duration=1 playlist-location=/var/www/localhost/html/stream.m3u8 location=/var/www/localhost/html/segment%6d.ts split. ! queue ! rtpjpegpay ! udpsink host=127.0.0.1 port=5000 &

detection &

logger "SmartCam running"
