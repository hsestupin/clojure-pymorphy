# -*- coding: utf-8 -*-
from pymorphy import get_morph

morph = get_morph(db_path, 'shelve')

def normalize(w):
    return morph.inflect_ru(unicode(str(w), 'utf8'), u'им,ед,мр')

#here we print the variable we set in Java
print(input)

print("well normalized")
#and here we set some data in the object we passed.
response.add(normalize(input).encode('utf-8'))
