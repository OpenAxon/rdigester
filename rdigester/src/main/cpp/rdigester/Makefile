CC=g++
CFLAGS=-I/usr/include/ -I/Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home/include/ -I/Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/include/darwin/ -c -Wall -x c
LDFLAGS=-O3 -lcrypto -lssl
OUT=lib

OBJDIR = target/obj
SODIR = target/so
SRCDIR = src/main/cpp/rdigester

#all: rdigester librdigester.so librdigesterjni.jnilib
all: librdigesterjni.jnilib

#rdigester: main.o RDigester.o
#	$(CC) $(LDFLAGS) main.o RDigester.o -o rdigester

librdigesterjni.jnilib: com_evidence_tools_digest_RDigester.o
	$(CC) $(LDFLAGS) -dynamiclib -o librdigesterjni.jnilib com_evidence_tools_digest_RDigester.o

com_evidence_tools_digest_RDigester.o: com_evidence_tools_digest_RDigester.c
	$(CC) $(CFLAGS) com_evidence_tools_digest_RDigester.c

#librdigester.so: RDigester.o
#	$(CC) $(LDFLAGS) -shared -o librdigester.so

#main.o: main.cpp
#	$(CC) $(CFLAGS) main.cpp

#RDigester.o: RDigester.cpp
#	$(CC) $(CFLAGS) RDigester.cpp

clean:
	rm *o rdigester
