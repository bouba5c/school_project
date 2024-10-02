TEMPLATE = app
CONFIG += console c++20
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += \
        main.cpp \
    tst_Position.cpp \
    tst_bag.cpp \
    tst_board.cpp \
    tst_deck.cpp \
    tst_player.cpp \
       tst_tile.cpp


HEADERS += \
    catch.hpp


LIBS += -L$$PWD/../metier -lmetier

INCLUDEPATH += $$PWD/../metier
