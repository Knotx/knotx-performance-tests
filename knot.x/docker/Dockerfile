FROM knotx/knotx:$knotx_version

LABEL maintainer="Knot.x Project"

COPY ./knotx /usr/local/knotx

HEALTHCHECK --interval=5s --timeout=2s --retries=12 \
  CMD curl --silent --fail localhost:8092/healthcheck || exit 1

ENV TARGET_MOCK_DOMAIN=host.docker.internal

CMD [ "knotx", "run-knotx"]