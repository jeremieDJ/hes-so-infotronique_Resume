#class words : permet de prendre un mot aléatoire d'une liste
#L'objectif est de réaliser une classe utilitaire générique permettant de :
#
#   Charger une liste de mots en mémoire
#   Récupérer des mots d'une certaine taille
#   Générer des mots aléatoirement
#   Chercher si un mot existe dans la liste
#   Trouver les mots correspondants à un motif donné

import random
import click

class Words:
    def __init__(self, words=None, filename=None):
        if words:
            # Si une liste de mots est fournie, on l'utilise
            self.words_list = words
        elif filename:
            # Sinon, on charge les mots à partir d'un fichier
            with open(filename, 'r', encoding='utf-8') as fp:
                self.words_list = fp.read().splitlines()
        else:
            # Si ni liste de mots ni fichier n'est fourni, la liste est vide
            self.words_list = []
    def __iter__(self):
        # Itérateur linéaire simple
        return iter(self.words_list)

    def __next__(self):
        # Retourne le prochain mot pour l'itérateur
        return next(self.__iter__())

    def __len__(self):
        # Retourne le nombre de mots dans la liste
        return len(self.words_list)

    def __contains__(self, word):
        # Vérifie si un mot est dans la liste
        return word in self.words_list

    def get_random_iterator(self, word_length=None):
        # Retourne un itérateur aléatoire
        words = self.words_list
        if word_length:
            words = [word for word in self.words_list if len(word) == word_length]
        random.shuffle(words)
        return iter(words)

    def get_iterator(self, *lengths):
        # Retourne un itérateur sur des mots d'une longueur donnée
        return (word for word in self.words_list if len(word) in lengths)


 # Exemple d'utilisation
if __name__ == "__main__":

    # Ou charger depuis un fichier
    w = Words(filename='francais.txt')
    #w = Words(['toto', 'titi', 'tata'])

    # Itérateur linéaire
    for i, word in zip(range(5), w):
        print(f"{i}) {word}")

    # Itérateur aléatoire sur tous les mots
    it = w.get_random_iterator()

    # Itérateur aléatoire sur des mots de 5 lettres
    it5 = w.get_random_iterator(5)
    print(next(it5))  # Par exemple "table"
    print(next(it5))  # Par exemple "chien"

    # Itérateur aléatoire sur des mots de 8 lettres
    it8 = w.get_random_iterator(8)
    print(next(it8))  # Par exemple "ordinate"

    # Longueur de l'instance (nombre de mots)
    print(len(w))  # Nombre total de mots

    # Vérifier si un mot existe dans la liste
    print('chien' in w)  # True si 'chien' est dans la liste

    # Itérateur avancé pour des mots de 11 ou 14 lettres
    #for word in w.get_iterator(11, 14):
    #    print(word)