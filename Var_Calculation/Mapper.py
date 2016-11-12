import math
import csv


def var_cal(stock):
    rend = []
    i = 0
    row_num = len(stock)
    while i < row_num - 1:
        r = math.log(stock[i] / stock[i + 1])
        rend.append(r)
        i += 1
    # print rend
    average = sum(rend) / len(rend)
    s = 0
    for r in rend:
        s += (abs(r - average)) * (abs(r - average))
    std = math.sqrt(s / (len(rend) - 1))
    # print average, std

    data = [len(stock), sum(stock) / len(stock), average, std, 255 * average]
    # print data

    vol = data[3]  # Daily volatility
    rendd = data[2]  # Daily average return

    # VaR Calculation
    # az = 1000  # Number of stocks
    valuep = 1  # Value of portfolio
    hp = 1  # Holding period
    a = 0.95  # Confidence level (5%)

    par_var = []
    while hp <= row_num:
        par = abs(valuep * -1.644854 * vol * math.sqrt(hp))
        par_var.append(par)
        # print hp, " ", par
        hp += 1
    return par_var

if __name__ == '__main__':
    inputfile = open('table.csv', "rb")
    reader = csv.DictReader(inputfile)
    stock1 = []
    stock2 = []

    row_num = 0
    for row in reader:
        row_num += 1
        stock1.append(float(row['Open']))
        stock2.append(float(row['B']))
        # print row_num, " ", float(row['Open'])
    inputfile.close()

    par_var1 = var_cal(stock1)
    par_var2 = var_cal(stock2)
    for i in range(len(par_var1)):
        print '%s\t%s' % (i + 1, par_var1[i])
        print '%s\t%s' % (i + 1, par_var2[i])

# print par_var1
# print par_var2

