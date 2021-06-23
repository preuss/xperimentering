package dk.xpreuss.utils.timers.clock;

/**
 * <table class="striped">
 * <caption>Pattern Letters and Symbols</caption>
 * <thead>
 *  <tr><th scope="col">Symbol</th>   <th scope="col">Meaning</th>         <th scope="col">Presentation</th> <th scope="col">Examples</th>
 * </thead>
 * <tbody>
 *   <tr><th scope="row">Y</th>     <td>year</td>                                  <td>year</td>              <td>4; 04; 0004</td>
 *
 *   <tr><th scope="row">M</th>     <td>month</td>                                 <td>number</td>            <td>7; 07</td>
 *
 *   <tr><th scope="row">W</th>     <td>week</td>                                  <td>number</td>            <td>7; 07</td>
 *
 *   <tr><th scope="row">D</th>     <td>day</td>                                   <td>number</td>            <td>9; 09</td>
 *
 *   <tr><th scope="row">w</th>     <td>week</td>                                  <td>number</td>            <td>27</td>
 *
 *   <tr><th scope="row">h</th>     <td>hour</td>                                  <td>number</td>            <td>13</td>
 *
 *   <tr><th scope="row">m</th>     <td>minute</td>                                <td>number</td>            <td>30</td>
 *
 *   <tr><th scope="row">s</th>     <td>second</td>                                <td>number</td>            <td>55</td>
 *
 *   <tr><th scope="row">f</th>     <td>fraction-of-second</td>                    <td>fraction</td>          <td>978</td>
 *   <tr><th scope="row">S</th>     <td>fraction-of-second</td>                    <td>fraction</td>          <td>978</td>
 *
 *   <tr><th scope="row">?</th>     <td>milli-of-second</td>                       <td>number</td>            <td>123</td>
 *   <tr><th scope="row">?</th>     <td>micro-of-second</td>                       <td>number</td>            <td>123456</td>
 *   <tr><th scope="row">N?</th>     <td>nano-of-second</td>                        <td>number</td>            <td>123456789</td>
 *
 *   <tr><th scope="row">n</th>     <td>Represents digit(s) [0-9]</td>             <td>number</td>            <td>55</td>
 *
 *   <tr><th scope="row">p</th>     <td>pad next</td>                              <td>pad modifier</td>      <td>1</td>
 *
 *   <tr><th scope="row">'</th>     <td>escape for text</td>                       <td>delimiter</td>         <td></td>
 *   <tr><th scope="row">''</th>    <td>single quote</td>                          <td>literal</td>           <td>'</td>
 *   <tr><th scope="row">P</th>     <td>Indicator start duration (period)</td>     <td>indicator</td>          <td>P</td>
 *   <tr><th scope="row">T</th>     <td>Indicate start time duration</td>          <td>indicator</td>          <td>T</td>
 *   <tr><th scope="row">[</th>     <td>optional section start</td>                <td>indicator</td>         <td></td>
 *   <tr><th scope="row">]</th>     <td>optional section end</td>                  <td>indicator</td>         <td></td>
 *   <tr><th scope="row">{</th>     <td>optional section start</td>                <td>indicator</td>         <td></td>
 *   <tr><th scope="row">}</th>     <td>optional section end</td>                  <td>indicator</td>         <td></td>
 *   <tr><th scope="row">#</th>     <td>Number sign, separates time-interval</td>  <td>indicator</td>         <td></td>
 * </tbody>
 * </table>
 */
public enum FormatStyle {
	//Basic format: PYMDThhmmss EXAMPLE P00021015T103020
	//Extended format: PYYYY-MM-DDThh:mm:ss EXAMPLE P0002-10-15T10:30:20
	BASIC_ISO_FORMAT,
	EXTENDED_ISO_FORMAT;
}
