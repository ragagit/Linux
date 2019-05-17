import React from 'react'

//Stateless component
export const Loading1 = () => <h2>Loading...</h2>;
export const Loading2 = (props) => <h2>Loading {props.message} ...</h2>
export const Loading = ({message}) => <h2>Loading {message} ...</h2>

//export default Loading;