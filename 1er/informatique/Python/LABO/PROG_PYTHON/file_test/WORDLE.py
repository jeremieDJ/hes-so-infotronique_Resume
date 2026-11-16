import click
from rich.console import Console

console = Console()

@click.option('--Nbr_essaye', default=5, help='Number of try.')
@click.option('--Lg_mot', default=1, help='longeur mot.')




@click.command()
def cli():
    console.print("Texte avec arrière-plan bleu", style="bold white on blue")

if __name__ == '__main__':
    cli()