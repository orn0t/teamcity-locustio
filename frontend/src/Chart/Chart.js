// @flow strict
import {React} from "@jetbrains/teamcity-api"
import {utils} from "@jetbrains/teamcity-api";


class Chart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            locustData: false
        }
    }

    componentDidMount() {
        utils.requestJSON(`app/rest/builds/id:${this.props.location.buildId}/statistics`).then((data) => {
            let stats = {}
            data.property.forEach(a => {
                if (a.name.startsWith('locustio_')) {
                    stats[a.name] = parseFloat(a.value).toFixed(2)
                }
            })
            if (Object.keys(stats).length) {
                this.setState({
                    locustData: stats
                })
            }
        })
    }

    render() {
        return this.state.locustData ? (
            <div className="locustStats">
                <h3>Locust load performance:</h3>
                <table className="locustStatsTable">
                    <thead>
                    <tr>
                        <th># reqs</th>
                        <th># fails</th>
                        <th>Avg</th>
                        <th>Min</th>
                        <th>Max</th>
                        <th>Median</th>
                        <th>req/s</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>{this.state.locustData.locustio_requests}</td>
                        <td>{this.state.locustData.locustio_failures}</td>
                        <td>{this.state.locustData.locustio_average_time}</td>
                        <td>{this.state.locustData.locustio_min_time}</td>
                        <td>{this.state.locustData.locustio_max_time}</td>
                        <td>{this.state.locustData.locustio_median_time}</td>
                        <td>{this.state.locustData.locustio_rps}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        ) : null
    }
}

export default Chart