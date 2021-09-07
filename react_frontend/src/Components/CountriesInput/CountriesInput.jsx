import { Component } from 'react'
import { countries } from 'countries-list'
import './CountriesInput.css'

class CountriesInput extends Component {

    constructor(props) {
        super(props);
        this.writeCountry = this.writeCountry.bind(this);
        let arr = [];
        let names = [];
        let h = []
        for (let k of Object.keys(countries)) {
            names.push(countries[k].name);
        }
        names.sort();
        for (let j of names) {
            let b = false;
            let n = 0;
            while (!b && n < Object.keys(countries).length) {
                if (countries[Object.keys(countries)[n]].name === j) {
                    h.push(Object.keys(countries)[n])
                    b = true;
                }
                else n++;
            }
        }
        for (let k of h) {
            if (k !== "IL") {
                let obj = {}
                obj[k] = countries[k]
                arr.push(obj)
            }
        }
        this.state = {
            countries: arr,
            tags: [],
            currentCountry: ""
        }
    }
    writeCountry(countryObject) {
        console.log(countryObject)
        let ci = document.getElementById('country');
        ci.style.backgroundImage = `url('https://www.countryflags.io/${Object.keys(countryObject)[0].toLowerCase()}/shiny/32.png')`
        ci.value = countryObject[Object.keys(countryObject)[0]].name;
        document.getElementById('countries-list').style.display = "none";
        this.props.sendCountry(countryObject[Object.keys(countryObject)[0]].name)
    }
    closeList() {
        if (window.event.target.id !== "countries-list" && window.event.target.id !== "country") {
            document.getElementById('countries-list').style.display = "none";
        }
    }
    render() {
        return (
            <div className="ci-container">
                <input onClick={() => document.getElementById('countries-list').style.display = "block"} onFocus={()=>document.getElementById('country').setAttribute('readonly',"")} onBlur={()=>document.getElementById('country').removeAttribute('readonly')} type="text" name="country" id="country" placeholder="Country" style={{ backgroundImage: 'url("https://www.countryflags.io/dz/shiny/32.png")', cursor: "pointer" }} required /> 
                <ul id="countries-list" style={{ display: 'none' }}>
                    {
                        this.state.countries.map((e, i) =>
                            <li onClick={() => this.writeCountry(e)} key={i} style={{ backgroundImage: `url('https://www.countryflags.io/${Object.keys(e)[0].toLowerCase()}/shiny/32.png')` }}>
                                {e[Object.keys(e)[0]].name}
                            </li>
                        )
                    }
                </ul>
            </div>
        )
    }
}

export default CountriesInput;