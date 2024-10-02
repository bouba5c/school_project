QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

CONFIG += c++20

# You can make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    controllergui.cpp \
    gamemodedialog.cpp \
    main.cpp \
    MainWindow.cpp \
    Tile.cpp \
     Apple.cpp \
    Bag.cpp \
    Picker.cpp \
    Board.cpp \
    Position.cpp \
    Deck.cpp \
    Player.cpp \
    Model.cpp \
    PlayAgain.cpp \
    HowToPlay.cpp



HEADERS += \
    MainWindow.h \
    Tile.h \
    Apple.h \
    Bag.h \
    Picker.h \
    Board.h \
    Position.h \
    Deck.h \
    Player.h \
    Model.h \
    Color.h \
    State.h \
    controllergui.h \
    gamemodedialog.h \
    PlayAgain.h \
    HowToPlay.h

FORMS += \
    MainWindow.ui \
    controllergui.ui \
    gamemodedialog.ui \
    PlayAgain.ui \
    HowToPlay.ui
# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

RESOURCES += \
    images.qrc \
    images.qrc
