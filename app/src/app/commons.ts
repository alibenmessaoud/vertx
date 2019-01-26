export class Commons {
    private static colors = ['salmon', 'lightcoral', 'crimson', 'red', 'darkred', 'orange', 'gold', 'darkkhaki', 'moccasin', 'lemonchiffon',
        'lawngreen', 'limegreen', 'green', 'darkgreen', 'springgreen', 'darkseagreen', 'olive', 'olivedrab', 'aquamarine', 'lightseagreen',
        'cadetblue', 'darkcyan', 'teal', 'skyblue', 'lightsteelblue', 'dodgerblue', 'royalblue', 'darkblue', 'midnightblue', 'slateblue',
        'goldenrod', 'honeydew'];

    public static getRandomColor(): string {
        return this.colors[Math.floor(Math.random() * this.colors.length) + 0];
    }

    public static getGridConfig(id: string) {
        const grid22config = {
            xl: 2,
            lg: 2,
            md: 2,
            sm: 2,
            xs: 1
        };

        const grid21config = {
            xl: 1,
            lg: 1,
            md: 1,
            sm: 1,
            xs: 1
        };

        const grid12config = {
            xl: 2,
            lg: 2,
            md: 2,
            sm: 2,
            xs: 1
        };

        const grid11config = {
            xl: 1,
            lg: 1,
            md: 1,
            sm: 1,
            xs: 1
        };

        const config = {
            '11': {config: grid11config, cols: 1, rowHeight: '1:1'},
            '22': {config: grid22config, cols: 2, rowHeight: '1:2'},
            '12': {config: grid12config, cols: 2, rowHeight: '1:2'},
            '21': {config: grid21config, cols: 1, rowHeight: '1:1'}
        };
        return config[id ? id : '11'];
    }
}
