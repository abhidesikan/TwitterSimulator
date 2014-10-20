import powerlaw
import sys,ast

data = ast.literal_eval( sys.argv[1] )
results = powerlaw.Fit(data)
print "Gamma value", results.power_law.alpha
	

