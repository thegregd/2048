interface Props{
    board:number[][];
}

export default function Board({board}:Props){

    return (

        <table>

            <tbody>

            {board.map((row,r)=>

                <tr key={r}>

                    {row.map((cell,c)=>

                        <td key={c}>
                            {cell===0 ? "" : cell}
                        </td>

                    )}

                </tr>

            )}

            </tbody>

        </table>

    );
}