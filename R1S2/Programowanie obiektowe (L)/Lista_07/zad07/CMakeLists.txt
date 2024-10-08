cmake_minimum_required(VERSION 3.28)
project(zad07)

set(CMAKE_CXX_STANDARD 17)

add_executable(zad07 main.cpp
        Student.cpp
        Student.h
        Czlowiek.cpp
        Czlowiek.h
        ListaOsob.cpp
        ListaOsob.h)
