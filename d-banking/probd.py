pin = map(int,raw_input())
pattern = raw_input()
kept = []
i=0

def idx(p,c):
	return ord(p) - ord(c) + 1

l = len(pin)
	
for p in pattern:
	if 'Z' >= p and p >= 'A':
		i += idx(p,'A')
	else:
		kept += pin[i:i+idx(p,'a')]
		i+= idx(p,'a')

if i != l:
	print "non sequitur"
else:
	print sum(kept)