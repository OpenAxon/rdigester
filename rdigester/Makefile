CC=g++
CFLAGS=-I/usr/include/ -I/Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home/include/ -I/Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/include/darwin/ -c -Wall
LDFLAGS=-O3 -lcrypto -lssl
OUT=lib

OBJDIR = ./target/obj
SODIR = ./target/so
LIBDIR = ./src/universal/lib
BINDIR = ./src/universal/bin
SRCDIR = ./src/main/cpp/rdigester
MKDIR_P = mkdir -p

all: directories rdigester librdigesterjni.jnilib

rdigester: main.o RDigester.o
	$(CC) $(LDFLAGS) $(OBJDIR)/main.o $(OBJDIR)/RDigester.o -o $(BINDIR)/rdigester

librdigesterjni.jnilib: com_evidence_tools_digest_RDigester.o RDigester.o
	$(CC) $(LDFLAGS) -dynamiclib -o $(LIBDIR)/librdigesterjni.jnilib $(OBJDIR)/com_evidence_tools_digest_RDigester.o $(OBJDIR)/RDigester.o

com_evidence_tools_digest_RDigester.o: $(SRCDIR)/com_evidence_tools_digest_RDigester.c
	$(CC) $(CFLAGS) $(SRCDIR)/com_evidence_tools_digest_RDigester.c -o $(OBJDIR)/com_evidence_tools_digest_RDigester.o

librdigester.so: RDigester.o
	$(CC) $(LDFLAGS) -shared -o $(SODIR)/librdigester.so

main.o: $(SRCDIR)/main.cpp
	$(CC) $(CFLAGS) $(SRCDIR)/main.cpp -o $(OBJDIR)/main.o

RDigester.o: $(SRCDIR)/RDigester.cpp
	$(CC) $(CFLAGS) $(SRCDIR)/RDigester.cpp -o $(OBJDIR)/RDigester.o

directories:
	@if test ! -d $(OBJDIR); then mkdir -p $(OBJDIR); else : ; fi
	@if test ! -d $(OBJDIR); then mkdir -p $(OBJDIR); else : ; fi
	@if test ! -d $(LIBDIR); then mkdir -p $(LIBDIR); else : ; fi
	@if test ! -d $(BINDIR); then mkdir -p $(BINDIR); else : ; fi

clean:
	@if test -d $(OBJDIR); then rm -r $(OBJDIR)/* $(OBJDIR); else : ; fi
	@if test -d $(SODIR); then rm -r $(SODIR)/* $(SODIR); else : ; fi
	@if test -d $(LIBDIR); then rm -r $(LIBDIR)/* $(LIBDIR); else : ; fi
	@if test -d $(BINDIR); then rm -r $(BINDIR)/* $(BINDIR); else : ; fi
