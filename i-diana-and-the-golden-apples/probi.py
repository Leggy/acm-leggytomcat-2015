l, timeDiana, timeHumper, n, slowdown = map(int,raw_input().split())
timeDiana *= l
timeHumper *= l
aps = []
a = 0
for i in range(n):
	line = raw_input().split()
	aps.append(tuple(map(int,line)))

def convert_to_time(apple):
	return (apple[0]*slowdown*(l-apple[1]))

aps = sorted(aps, lambda x,y: convert_to_time(x)-convert_to_time(y))

for i in aps:
	if timeDiana + convert_to_time(i) < timeHumper:
		a+= i[0]
		timeDiana+=convert_to_time(i)
	else:
		break

if a <= 0:
	print "Diana marries Humperdonkey"
else:
	print a