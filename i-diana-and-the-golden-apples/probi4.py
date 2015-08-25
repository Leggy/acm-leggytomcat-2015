import sys
length, timeD, timeH, nApples, slowdown = map(int,raw_input().split())
timeD *= length
timeH *= length	
apples = []

if timeD >= timeH:
	print "Diana marries Humperdonkey"
	sys.exit(0)

def timeC(weight, position):
	return slowdown*weight*(length-position)


for i in range(nApples):
	apples.append(tuple(map(int,raw_input().split())))


def take(weight,init_time, apples):
	if init_time >= timeH or not apples:
		return weight
	if init_time+timeC(*apples[0]) >= timeH:
		check1 = 0
	else:
		check1 = take(apples[0][0]+weight, init_time+timeC(*apples[0]), apples[1:])
	check2 = take(weight, init_time, apples[1:])
	return max(check1, check2)

print take(0,timeD,apples)