import sys
import re

from bst import *


file_names = []
	

atoms = ["ID", "CONST", "int", "real", "define", "while", "if", "read", "write", ";", "->", "{", "}", "=", "+", "-", "*", "/", "%", "==", "!=", ">=", "<=", ">", "<"]
atom_code = {}



def is_ID(s):
	"""
	Checks if the given string s is a valid ID
	"""
	if len(s) == 0 or len(s) > 250:
		return False
	result = bool(re.match("^[A-Za-z]+\d*$", s))
	return result

def is_const(s):
	"""
	Checks if the given string s is a CONST
	"""
	if len(s) == 0:
		return False
	result = bool(re.match("^((\+|-)?(\d+)(\.\d+)?)$", s))
	return result

def is_string(s):
	if len(s) == 0:
		return False
	result = bool(re.match("'[a-zA-Z ]*'", s))
	return result

def removeInstructionEnd(s):
	"""
	Removes the instrucion end sign ";" from the string
	if it exists and returns the result
	"""
	if len(s) == 0:
		return s
	if s[-1] == ";":
		return s[:-1]
	return s


def splitSemiColumn(program):

	result = []

	for s in program:
		if s[-1] == ";" and len(s) > 1:
			x = s[:-1]
			y = s[-1]
			result.append(x)
			result.append(y)
			continue
		result.append(s)

	return result


def combineString(program):

	result = []

	st = ""
	openString = False

	for s in program:
		if openString:
			st += s
			st += " "
			if s[-1] == "'":
				openString = False
				result.append(st[:-1])
				st = ""
				continue

		if s[0] == "'":
			openString = True
			st += s
			st += " "
			continue

		result.append(s)
	# print(result)
	return result

def separateProgram(program):
	
	declaration_list = []
	instruction_list = []

	inDecl = True
	for s in program:
		if s == "{":
			inDecl = False
		if inDecl:
			declaration_list.append(s)
		else:
			instruction_list.append(s)

	return declaration_list, instruction_list



def formatProgram(program):
	# print(splitSemiColumn(program))
	# print(combineString(splitSemiColumn(program)))
	return combineString(splitSemiColumn(program))


def analyze(filename):


	fip = [] # list of tuples 
	ts_id = BST()
	ts_const = BST()

	id_nr = 0
	const_nr = 0

	program = []

	with open(filename, "r") as f:
		for line in f:
			parts = line.split()
			for s in parts:
				program.append(s)
	
	decl_list, instr_list = separateProgram(program)
	program = formatProgram(program)


	for symbol in program:
		# if symbol is found in the atom_code dictionary, it means it is not an ID or a CONST and we can assign it an atom code without the need of a symbol table code
		if symbol in atom_code:
			fip.append((atom_code[symbol], None)) 
		else:
			if is_ID(symbol):
				value = ts_id.find(symbol)
				if value != None:
					fip.append((atom_code["ID"], value))
				else:
					ts_id.insert(symbol, id_nr)
					fip.append((atom_code["ID"], id_nr))
					id_nr += 1
				continue
			if is_const(symbol) or is_string(symbol):
				value = ts_const.find(symbol)
				if value != None:
					fip.append((atom_code["CONST"], value))
				else:
					ts_const.insert(symbol, const_nr)
					fip.append((atom_code["CONST"], const_nr))
					const_nr += 1
				continue
			print("Unidentified Symbol: {}".format(symbol))
			print("Terminating Operation!")
			quit()


	# Show results
	print("------------------------------------------------------------------------")
	for i in range(len(program)):
		print("{} | {} - {}".format(program[i], fip[i][0], fip[i][1]))
	print("------------------------------------------------------------------------")


if __name__ == "__main__":

	for i in range(len(atoms)):
		atom_code[atoms[i]] = i

	# print(sys.argv)
	file_names = sys.argv[1:]
	# print(is_const("-2."))
	# print(file_names)
	for file in file_names:
		print(file)
		analyze(file)
		print()

	# print(is_string("'ceva altceva'"))


