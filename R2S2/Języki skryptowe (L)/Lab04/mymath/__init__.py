# This file can be empty or contain package initialization code if needed.

__author__ = "Guliveer"
__version__ = "1.0.0"

# Importing all functions from the submodules
from .myalgebra import *
from .zad01.functions import *
from .zad02.functions import *

def get_author():
    return __author__

def get_version():
    return __version__