def cache(f):
	d = {}
	def g(*args):
		args = tuple(args)
		if d.get(args):
			return d[args]
		d[args] = f(*args)
		return d[args]
	return g

length, timeD, timeH, nApples, slowdown = map(int,raw_input().split())
timeD *= length
timeH *= length	
apples = []

def timeFromPair(weight, position):
	return slowdown*weight*(length-position)


for i in range(nApples):
	apples.append(tuple(map(int,raw_input().split())))
apples = sorted(apples,key = lambda x: timeFromPair(*x))

def fn(apples, timeD):
	if not apples:
		return (0,timeD)
	check1 = fn(apples[1:],timeD+timeFromPair(*apples[0]))
	check2 = fn(apples[1:], timeD)
	if check1[0] + apples[0][0] > check2[0]:
		return check1
	else:
		return check2

print fn(apples,timeD)