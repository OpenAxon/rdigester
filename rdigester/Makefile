CC=g++
CFLAGS=-I/usr/include/ -I/Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home/include/ -I/Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/include/darwin/ -c -Wall -x c
LDFLAGS=-O3 -lcrypto -lssl
OUT=lib

OBJDIR = ./target/obj
SODIR = ./target/so
SRCDIR = ./src/main/cpp/rdigester

#all: rdigester $(SODIR)/librdigester.so $(SODIR)/librdigesterjni.jnilib
all: $(SODIR)/librdigesterjni.jnilib

#rdigester: $(OBJDIR)/main.o $(OBJDIR)/RDigester.o
#	$(CC) $(LDFLAGS) $(OBJDIR)/main.o $(OBJDIR)/RDigester.o -o $(OBJDIR)/rdigester

$(SODIR)/librdigesterjni.jnilib: $(OBJDIR)/com_evidence_tools_digest_RDigester.o
	$(CC) $(LDFLAGS) -dynamiclib -o $(SODIR)/librdigesterjni.jnilib $(OBJDIR)/com_evidence_tools_digest_RDigester.o

$(OBJDIR)/com_evidence_tools_digest_RDigester.o: $(SRCDIR)/com_evidence_tools_digest_RDigester.c
	$(CC) $(CFLAGS) $(SRCDIR)/com_evidence_tools_digest_RDigester.c -o $(OBJDIR)/com_evidence_tools_digest_RDigester.o

#librdigester.so: RDigester.o
#	$(CC) $(LDFLAGS) -shared -o librdigester.so

#main.o: main.cpp
#	$(CC) $(CFLAGS) main.cpp

#RDigester.o: RDigester.cpp
#	$(CC) $(CFLAGS) RDigester.cpp

clean:
	rm *o rdigester
