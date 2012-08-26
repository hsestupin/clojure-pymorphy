# -*- coding: utf-8 -*-
from pymorphy import get_morph

morph = get_morph(db_path, 'shelve')

def normalize(w):
    return morph.inflect_ru(unicode(str(w), 'utf8'), u'им,ед,мр')

# Input can be as one word or words separated with comma
# For example, there are correct input cases:
# "word"
# "word1,word2,,,,"

for word in input.split(','):
    response.add(normalize(word).encode('utf-8'))
