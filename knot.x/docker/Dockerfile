FROM knotx/knotx:$knotx_version

LABEL maintainer="Knot.x Project"

COPY ./knotx /usr/local/knotx

HEALTHCHECK --interval=5s --timeout=2s --retries=12 \
  CMD curl --silent --fail localhost:8092/healthcheck || exit 1

CMD [ "knotx", "run-knotx", "-Dconfig.server.options.config.mocks.domain=192.168.36.14"]

#, "-Dconfig.server.options.config.domain=192.168.36.14"