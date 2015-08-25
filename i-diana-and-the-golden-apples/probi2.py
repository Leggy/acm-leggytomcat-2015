from sys import exit
length, timeD, timeH, nApples, slowdown = map(int,raw_input().split())
timeD *= length
timeH *= length
apples = []
keptWeight = 0
count = 0

def timeFromPair(weight, position):
	return slowdown*weight*(length-position)


for i in range(nApples):
	apples.append(tuple(map(int,raw_input().split())))
apples = sorted(apples,key = lambda x: timeFromPair(*x))

if timeD >= timeH:
	print "Diana marries Humperdonkey"
	exit(0)

for weight, position in apples:
	if timeD + timeFromPair(weight,position) >= timeH:
		break
	else:
		timeD+=timeFromPair(weight,position)
		keptWeight += weight

print keptWeight