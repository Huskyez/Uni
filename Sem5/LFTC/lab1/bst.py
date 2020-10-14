

class Node:
	def __init__(self, key, value):
		self.left = None
		self.right = None
		self.key = key
		self.value = value

class BST:
	def __init__(self, *args):
		self.root = None

		initial = []

		if len(args) == 1:
			if isinstance(args[0], list):
				initial = args[0]

		if len(initial) > 0:
			for x in initial:
				self.insert(x.key, x.value)

	def insert(self, key, value):
		
		if self.root == None:
			self.root = Node(key, value)
			return

		child = Node(key, value)

		prev = self.root
		x = self.root
		while x != None:
			prev = x
			if child.key < x.key:
				x = x.left
			else:
				x = x.right

		if child.key < prev.key:
			prev.left = child
		else:
			prev.right = child

	def find(self, key):
		if self.root == None:
			return None

		x = self.root

		while x != None:
			if x.key == key:
				return x.value
			if key < x.key:
				x = x.left
			else:
				x = x.right

		return None
