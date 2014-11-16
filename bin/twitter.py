import powerlaw
import sys,ast

filename = sys.argv[1]

f = open(filename,'r')

data = ast.literal_eval( f.read() )
results = powerlaw.Fit(data)
print "Gamma value", results.power_law.alpha
	

