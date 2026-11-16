import click

def abort_if_false(ctx, param, value):
    if not value:
        ctx.abort()

@click.command()
@click.option('--count', default=1, help='Number of greetings.')

@click.option('--name', prompt='Your name',
              help='The person to greet.')

@click.option('--yes', is_flag=True, callback=abort_if_false,
              expose_value=False,
              prompt='Tu veux vraiment appeler ?')

def hello(count, name):
    """Simple program that greets NAME for a total of COUNT times."""
    for x in range(count):
        click.echo(f"Hello {name}!")

if __name__ == '__main__':
    hello()

def dropdb():
    click.echo('Dropped all tables!')

dropdb()


