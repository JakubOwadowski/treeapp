const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {nodes: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/tree'}).done(response => {
			this.setState({nodes: response.entity._embedded.nodes});
		});
	}

	render() {
		return (
			<NodeList nodes={this.state.nodes}/>
		)
	}
}

class NodeList extends React.Component{
	render() {
		const nodes = this.props.nodes.map(node =>
			<Node key={node._links.self.href} node={node}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>ID</th>
						<th>Parent ID</th>
						<th>Value</th>
					</tr>
					{nodes}
				</tbody>
			</table>
		)
	}
}

class Node extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.node.id}</td>
				<td>{this.props.node.parent_id}</td>
				<td>{this.props.node.value}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)