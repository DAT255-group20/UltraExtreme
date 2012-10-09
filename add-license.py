#!/usr/bin/python3

import os

# Folders that should be checked
FOLDERS = (
        'src',
        'tests/UltraExtremeTest/src')

# License and copyright text
LICENSE = \
"/* ============================================================\n\
 * Copyright 2012 Bjorn Persson Mattsson, Johan Gronvall, Daniel Jonsson,\n\
 * Viktor Anderling\n\
 *\n\
 * This file is part of UltraExtreme.\n\
 *\n\
 * UltraExtreme is free software: you can redistribute it and/or modify\n\
 * it under the terms of the GNU General Public License as published by\n\
 * the Free Software Foundation, either version 3 of the License, or\n\
 * (at your option) any later version.\n\
 *\n\
 * UltraExtreme is distributed in the hope that it will be useful,\n\
 * but WITHOUT ANY WARRANTY; without even the implied warranty of\n\
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the\n\
 * GNU General Public License for more details.\n\
 *\n\
 * You should have received a copy of the GNU General Public License\n\
 * along with UltraExtreme. If not, see <http://www.gnu.org/licenses/>.\n\
 * ============================================================ */\n\
\n"

def main():
    # Get a list of the paths to all .java files
    file_list = list()
    for folder in FOLDERS:
        # Walk through all the subfolders and stuff
        for dirname, dirnames, filenames in os.walk(folder):
            for filename in filenames:
                # If the file ends with .java, save it in the file_list
                if filename[-5:] == '.java':
                    file_list.append(os.path.join(dirname, filename))

    # Add license stuff to all the files in file_list
    for filepath in file_list:
        # Open the file and create a file object
        f = open(filepath, "rt")
        # Read the lines in the file
        lines = f.readlines()
        f.close()
        # Check line number 5, and let that say if the license has been added to
        # the file
        try:
            if lines[4] != " * This file is part of UltraExtreme.\n":
                add_license_to_file(filepath)
        except IndexError:
            # No line 5 in file
            add_license_to_file(filepath)

def add_license_to_file(filepath):
    # Open the file and create new text with license stuff
    f = open(filepath, "rt")
    text = f.read()
    text = LICENSE + text
    f.close()

    # Write the stuff to the file
    f = open(filepath, "wt")
    f.write(text)
    f.close()

if __name__ == '__main__':
    main()
