FROM confluentinc/cp-kafka-connect:7.4.0
USER root

# JDBC sink (you already had this working)
RUN confluent-hub install --no-prompt \
      confluentinc/kafka-connect-jdbc:10.8.4 \
    && rm -rf /tmp/confluent-hub-cache

# ElasticSearch sink
RUN confluent-hub install --no-prompt \
      confluentinc/kafka-connect-elasticsearch:13.0.3 \
    && rm -rf /tmp/confluent-hub-cache

USER appuser
ENV CONNECT_PLUGIN_PATH=/usr/share/confluent-hub-components,/usr/share/java
