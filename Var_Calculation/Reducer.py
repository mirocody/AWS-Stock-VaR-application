import sys

current_hp = 0
current_var = 0
var_sum = {}

# input comes from STDIN
for line in sys.stdin:
    # remove leading and trailing whitespace
    line = line.strip()

    # parse the input we got from mapper.py
    hp, var = line.split('\t', 1)

    # convert count (currently a string) to int
    hp = int(hp)
    var = float(var)

    if hp in var_sum:
        var_sum[hp] += var
    else:
        var_sum[hp] = var

for i in range(len(var_sum)):
    if i in var_sum:
        print i, var_sum[i]

