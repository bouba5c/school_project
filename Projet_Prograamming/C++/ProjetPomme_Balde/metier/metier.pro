TEMPLATE = lib
CONFIG += console c++20
CONFIG -= app_bundle
CONFIG -= qt

DESTDIR = $$PWD

SOURCES += \
        Tile.cpp \
        Apple.cpp \
        Bag.cpp \
        Picker.cpp \
        Board.cpp \
        Position.cpp \
        Deck.cpp \
        Player.cpp \
        Model.cpp \
        View.cpp \
        Controller.cpp \

HEADERS += \
        Tile.h \
        Apple.h \
        Bag.h \
        Picker.h \
        Board.h \
        Position.h \
        Deck.h \
        Player.h \
        Model.h \
        View.h \
        Controller.h \
        Color.h \
        State.h \
