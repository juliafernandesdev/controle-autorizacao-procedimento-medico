FROM jboss/wildfly:36.0.1.Final

COPY target/controle-autorizacao-procedimento-medico.war /opt/jboss/wildfly/standalone/deployments/ROOT.war