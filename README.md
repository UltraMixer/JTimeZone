# JTimeZone

(c) UltraMixer Digital Audio Solutions
www.ultramixer.com


#What is JTimeZone?

JTimeZone is a java library to get the real current default time zone. 
Additionally it can be used to detect system time zone changes at runtime.


#What's the problem with the Java built in TimeZone class?

The TimeZone class does not recognise changes of the system time zone until you restart your Java process. So we need to write
native code for Windows and Mac OS X to detect these changes during Java runtime.


#Maven

    <dependency>
        <groupId>com.ultramixer</groupId>
        <artifactId>jtimezone-core</artifactId>
        <version>1.0.0</version>
    </dependency>

    <dependency>
        <groupId>com.ultramixer</groupId>
        <artifactId>jtimezone-win</artifactId>
        <version>1.0.0</version>
    </dependency>

    <dependency>
        <groupId>com.ultramixer</groupId>
        <artifactId>jtimezone-mac</artifactId>
        <version>1.0.0</version>
    </dependency>


#Licence

Free to use private and commercially. Please include a note to our company "UltraMixer Digital Audio Solutions / www.ultramixer.com".
