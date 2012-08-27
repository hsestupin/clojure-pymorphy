# -*- coding: utf-8 -*-

# Input can be as one word or words separated with comma
# For example, there are correct input cases:
# "word"
# "word1,word2,,,,"

def normalize(input):
    result = []
    for word in input.split(','):
        normalized = morph.inflect_ru(unicode(str(word), 'utf8'), u'им,ед,мр')
        result.append(normalized.encode('utf-8'))
    return ','.join(result)
