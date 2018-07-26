# ###########################################################
# Dockerfile
# Based on Liferay 7.1 GA1
# ###########################################################
# Set the base image to Liferay 7.1 GA1

FROM mdelapenya/liferay-portal:7.1-ga1

# File Author / Maintainer

MAINTAINER Alejandro Hernández <alejandro.hernandez@liferay.com>

# Expose debug port

EXPOSE 5005

# Expose JMX port

EXPOSE 8099

# Expose GOGO port

EXPOSE 11311

# Copy portal-ext.properties

COPY portal-ext.properties $LIFERAY_HOME/portal-ext.properties

# Remove old Apio Architect

RUN rm $LIFERAY_HOME/osgi/marketplace/*Apio*
RUN rm $LIFERAY_HOME/osgi/configs/*apio*

# Prepare Liferay

RUN mkdir $LIFERAY_DEPLOY_DIR
RUN mkdir -p $LIFERAY_CONFIG_DIR/osgi

# Install new Apio Architect

RUN wget http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.impl/1.0.6/com.liferay.apio.architect.impl-1.0.6.jar \
    http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.api/1.3.0/com.liferay.apio.architect.api-1.3.0.jar \
    http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.uri.mapper.impl/1.0.1/com.liferay.apio.architect.uri.mapper.impl-1.0.1.jar \
    http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.exception.mapper.impl/1.0.3/com.liferay.apio.architect.exception.mapper.impl-1.0.3.jar
RUN mv *apio.architect*.jar $LIFERAY_DEPLOY_DIR
RUN wget https://raw.githubusercontent.com/liferay/com-liferay-apio-architect/e25b53280939dd1f5bc4eab889c9ad7d512314cb/apio-architect-impl/configs/com.liferay.apio.architect.impl.application.ApioApplication-default.config
RUN mv com.liferay.apio.architect.impl.application.ApioApplication-default.config $LIFERAY_CONFIG_DIR/osgi

# Install Demo Data Creators

RUN wget http://central.maven.org/maven2/com/liferay/com.liferay.document.library.demo.data.creator.impl/2.0.0/com.liferay.document.library.demo.data.creator.impl-2.0.0.jar \
    http://central.maven.org/maven2/com/liferay/com.liferay.document.library.demo.data.creator.api/2.0.0/com.liferay.document.library.demo.data.creator.api-2.0.0.jar \
    http://central.maven.org/maven2/com/liferay/com.liferay.roles.admin.demo.data.creator.api/2.0.0/com.liferay.roles.admin.demo.data.creator.api-2.0.0.jar \
    http://central.maven.org/maven2/com/liferay/com.liferay.roles.admin.demo.data.creator.impl/2.0.1/com.liferay.roles.admin.demo.data.creator.impl-2.0.1.jar \
    http://central.maven.org/maven2/com/liferay/com.liferay.users.admin.demo.data.creator.api/2.0.0/com.liferay.users.admin.demo.data.creator.api-2.0.0.jar \
    http://central.maven.org/maven2/com/liferay/com.liferay.users.admin.demo.data.creator.impl/2.0.0/com.liferay.users.admin.demo.data.creator.impl-2.0.0.jar
RUN mv *demo.data*.jar $LIFERAY_DEPLOY_DIR

# Set JAVA_OPTS

ENV JAVA_OPTS "-Djava.rmi.server.hostname=localhost -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.rmi.port=8099 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.local.only=false -Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n"