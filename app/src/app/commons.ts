export class Commons {
    private static colors = ['salmon', 'lightcoral', 'crimson', 'red', 'darkred', 'orange', 'gold', 'darkkhaki', 'moccasin', 'lemonchiffon',
        'lawngreen', 'limegreen', 'green', 'darkgreen', 'springgreen', 'darkseagreen', 'olive', 'olivedrab', 'aquamarine', 'lightseagreen',
        'cadetblue', 'darkcyan', 'teal', 'skyblue', 'lightsteelblue', 'dodgerblue', 'royalblue', 'darkblue', 'midnightblue', 'slateblue',
        'goldenrod', 'honeydew'];

    public static getRandomColor(): string {
        return this.colors[Math.floor(Math.random() * this.colors.length) + 0];
    }
}
