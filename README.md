<?xml version="1.0" encoding="UTF-8"?>

<mule xmlns:dw="http://www.mulesoft.org/schema/mule/ee/dw" xmlns:metadata="http://www.mulesoft.org/schema/mule/metadata" xmlns:http="http://www.mulesoft.org/schema/mule/http" xmlns:mulexml="http://www.mulesoft.org/schema/mule/xml" xmlns="http://www.mulesoft.org/schema/mule/core" xmlns:doc="http://www.mulesoft.org/schema/mule/documentation"
	xmlns:spring="http://www.springframework.org/schema/beans" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-current.xsd
http://www.mulesoft.org/schema/mule/core http://www.mulesoft.org/schema/mule/core/current/mule.xsd
http://www.mulesoft.org/schema/mule/http http://www.mulesoft.org/schema/mule/http/current/mule-http.xsd
http://www.mulesoft.org/schema/mule/ee/dw http://www.mulesoft.org/schema/mule/ee/dw/current/dw.xsd
http://www.mulesoft.org/schema/mule/xml http://www.mulesoft.org/schema/mule/xml/current/mule-xml.xsd">
    <http:listener-config name="HTTP_Listener_Configuration" host="0.0.0.0" port="9082" doc:name="HTTP Listener Configuration"/>
    <flow name="dataweave_demoFlow">
        <http:listener config-ref="HTTP_Listener_Configuration" path="/weave" doc:name="HTTP"/>
        <mulexml:dom-to-xml-transformer doc:name="DOM to XML"/>
        <set-variable variableName="token" value="#[payload]" mimeType="application/xml" doc:name="Variable"/>
        <set-payload value="&lt;soap:Envelope xmlns:soap=&quot;http://www.w3.org/2003/05/soap-envelope&quot; 
    xmlns:wsa=&quot;http://www.w3.org/2005/08/addressing&quot; 
    xmlns:sfl=&quot;https://bac.com/wsdl/SFLeadAdapterService/SFLeadAdapterService&quot; 
    xmlns:atl=&quot;http://atlas.countrywide.com&quot;&gt;
    &lt;soap:Header&gt;
        &lt;Security xmlns=&quot;http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd&quot;&gt;
            &lt;UsernameToken xmlns=&quot;http://bac.com/xmlschema/enterprise/InfrastructureV003&quot;&gt;
                &lt;Username&gt;SFHL&lt;/Username&gt;
                &lt;Password&gt;password&lt;/Password&gt;
            &lt;/UsernameToken&gt;
        &lt;/Security&gt;
        &lt;wsa:ReplyTo soap:mustUnderstand=&quot;1&quot;&gt;
            &lt;wsa:Address&gt;http://www.w3.org/2005/08/addressing/anonymous&lt;/wsa:Address&gt;
        &lt;/wsa:ReplyTo&gt;
        &lt;wsa:To soap:mustUnderstand=&quot;1&quot;&gt;https://bac.com/wsdl/SFLeadAdapterService.svc&lt;/wsa:To&gt;
        &lt;wsa:Action&gt;https://bac.com/wsdl/SFLeadAdapterService/SFLeadAdapterService/ISFLeadAdapterService/CustSearchByFullSSN&lt;/wsa:Action&gt;
        &lt;RequestHeader xmlns=&quot;http://atlas.countrywide.com&quot; 
            xmlns:xsd=&quot;http://www.w3.org/2001/XMLSchema&quot; 
            xmlns:xsi=&quot;http://www.w3.org/2001/XMLSchema-instance&quot;&gt;
            &lt;SourceSystem sourceSystemType=&quot;Salesforce&quot; sourceSystemDtTm=&quot;11/24/2019 18:06:10&quot; sourceSystemName=&quot;Salesforce&quot; callChainInitiatedSourceSystemName=&quot;&quot; callChainInitiatedSourceSystemList=&quot;&quot;/&gt;
            &lt;MessageGenerator generatorName=&quot;SFClient&quot; generatorVersion=&quot;Unknown&quot; moduleName=&quot;SFLookup&quot; createDate=&quot;&quot; lastModifiedDate=&quot;&quot;/&gt;
            &lt;RequestInfo requestId=&quot;&quot; callChainId=&quot;&quot; contextId=&quot;&quot; sessionId=&quot;&quot; logThisMessageFlag=&quot;false&quot; eventId=&quot;&quot; transitionId=&quot;&quot;/&gt;
            &lt;LoginName networkUserId=&quot;nbkqirz&quot; networkDomainName=&quot;CORP&quot; networkDomainType=&quot;NTLM&quot; firstName=&quot;&quot; lastName=&quot;&quot;/&gt;
            &lt;EnvironmentCd code=&quot;iOSIT1&quot;/&gt;
        &lt;/RequestHeader&gt;
    &lt;/soap:Header&gt;
    &lt;soap:Body&gt;
        &lt;sfl:CustomerSearchJsonRequest&gt;&lt;![CDATA[{&quot;CustomerSearchCriteria&quot;:{&quot;UsTrustConsentFlag&quot;:null,&quot;UserProfileName&quot;:null,&quot;TaxIdNum4&quot;:null,&quot;TaxIdNum&quot;:&quot;666112222&quot;,&quot;SessionID&quot;:&quot;fd49e5e6-8159-06f6-f635-b8edb233a357&quot;,&quot;ReservationNum&quot;:null,&quot;Phone&quot;:null,&quot;PartyId&quot;:null,&quot;NetworkUserId&quot;:&quot;nbkqirz&quot;,&quot;MasterProfileID&quot;:null,&quot;LoanAcctNum&quot;:null,&quot;LastName&quot;:null,&quot;IsTrustCustomer&quot;:null,&quot;IsMerrillOnlyFlag&quot;:null,&quot;IsFiduciaryFlag&quot;:null,&quot;GlobalCustomerIndicatorID&quot;:null,&quot;FirstName&quot;:null}}]]&gt;&lt;/sfl:CustomerSearchJsonRequest&gt;
    &lt;/soap:Body&gt;
&lt;/soap:Envelope&gt;" mimeType="application/xml" doc:name="Set Payload"/>
        <logger message="#[payload]" level="INFO" doc:name="Logger"/>
        <dw:transform-message doc:name="Transform Message" metadata:id="e07e9769-ff22-45a1-9885-8549a7ac24d6">
            <dw:input-payload mimeType="application/xml"/>
            <dw:input-variable mimeType="application/xml" variableName="token"/>
            <dw:set-payload><![CDATA[%dw 1.0
%output application/xml
%namespace ns0 http://www.w3.org/2003/05/soap-envelope
%namespace ns5 http://atlas.countrywide.com
%namespace ns4 http://www.w3.org/2005/08/addressing
%namespace ns1 http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd
%namespace ns2 urn:oasis:names:tc:SAML:2.0:assertion
%namespace ns3 http://www.w3.org/2000/09/xmldsig#
---
{
	ns0#Envelope: {
		ns0#Header: {
			ns1#Security: flowVars.token
				,
			ns4#ReplyTo: {
				ns4#Address: payload.ns0#Envelope.ns0#Header.ns4#ReplyTo.ns4#Address
			},
			ns4#To: payload.ns0#Envelope.ns0#Header.ns4#To,
			ns4#Action: payload.ns0#Envelope.ns0#Header.ns4#Action,
			ns5#RequestHeader: {
				ns5#SourceSystem: payload.ns0#Envelope.ns0#Header.ns5#RequestHeader.ns5#SourceSystem,
				ns5#MessageGenerator: payload.ns0#Envelope.ns0#Header.ns5#RequestHeader.ns5#MessageGenerator,
				ns5#RequestInfo: payload.ns0#Envelope.ns0#Header.ns5#RequestHeader.ns5#RequestInfo,
				ns5#LoginName: payload.ns0#Envelope.ns0#Header.ns5#RequestHeader.ns5#LoginName,
				ns5#EnvironmentCd: payload.ns0#Envelope.ns0#Header.ns5#RequestHeader.ns5#EnvironmentCd
			}
		},
		ns0#Body: payload.ns0#Envelope.ns0#Body
	}
}]]></dw:set-payload>
        </dw:transform-message>
        <logger message="Final ----&gt;#[payload]" level="INFO" doc:name="Logger"/>
    </flow>
</mule>
