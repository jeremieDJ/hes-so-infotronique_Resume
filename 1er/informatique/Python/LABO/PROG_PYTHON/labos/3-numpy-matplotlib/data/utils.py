def read_file(path):
    f = open(path,'r')
    
    ct = [l.split(';') for l in f.read().split('\n')]
    f.close()
    return ct[0],ct[1:]
    