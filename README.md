# JTimeZone

(c) UltraMixer Digital Audio Solutions
www.ultramixer.com


==================
What is JTimeZone?
==================
JTimeZone is a java library to get the real current default time zone. 
Additionally it can be used to detect system time zone changes at runtime.


=========================================================
What's the problem with the Java built in TimeZone class?
=========================================================

The TimeZone class does not recognise changes of the system time zone until you restart your Java process. So we need to write
native code for Windows and Mac OS X to detect these changes during Java runtime.



=======
Licence
=======
Free to use private and commercially. Please include a note to our company "UltraMixer Digital Audio Solutions / www.ultramixer.com".
