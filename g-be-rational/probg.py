from fractions import Fraction
line = raw_input()
whole = line.partition(".")[0]
dec = line.partition(".")[2].partition("(")[0]
rep_dec = line.partition("(")[2][:-1]
n = Fraction(0)
if whole:
	n += Fraction(int(whole),1)
if dec:
	n += Fraction(int(dec), 10**(len(dec)))
if rep_dec:
	n += Fraction(int(rep_dec), 10**(len(dec)) * (10**len(rep_dec) -1))
	
if n.denominator == 1:
	print str(n)+"/1"
else:
	print n