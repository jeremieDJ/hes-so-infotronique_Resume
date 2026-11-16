import random
import click

class Words:

    def __init__(self):
        # Ouvre le fichier et lit son contenu
        with open('C:\\Users\\mimod\\Documents\\PROG_PYTHON\\wordle\\francais.txt', 'r', encoding='utf-8') as fp:
            content = fp.read()  # Lire tout le contenu du fichier
        self.words_list = content.splitlines()  # Diviser le contenu en lignes
        #print(f"Mots chargés : {self.words_list}")  # Imprime la liste de tous les mots

    def longueur_liste(self, longueur_souhaitee=5):
        # Retourne une liste constituée des mots de la bonne longueur

       # Filtrer les mots par longueur
        mots_filtrés = [mot for mot in self.words_list if len(mot) == longueur_souhaitee]
       # print(f"Mots de longueur {longueur_souhaitee} : {mots_filtrés}")  # Imprime les mots filtrés
        return mots_filtrés

    def get_random_word(self, longueur_souhaitee=5):
       # Obtenir les mots de la longueur souhaitée
        mots_corrects = self.longueur_liste(longueur_souhaitee)
        return random.choice(mots_corrects)  # Retourne un mot aléatoire parmi les mots filtrés

@click.command()
@click.option('-n', default=5, help='Longueur du mot souhaitée')
def main(n):
    # Exemple d'utilisation
    words_instance = Words()  # Créer une instance de la classe

    # Obtenir un mot aléatoire de longueur n   
    random_word = words_instance.get_random_word(n)

    print(f"Mot aléatoire de {n} caractères : {random_word}")

#Lancer le programme principal
if __name__ == "__main__":
    main()