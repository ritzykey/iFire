import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';


const Photos = () => {

    const { id } = useParams();

    const [imgData, setImgData] = useState();



    useEffect(() => {
        axios.get(`/api/v1/photos/${id}`).then(res => {
            setImgData(res.data.image.data)
        })
    }, []);



    return <>
        <h1>View Photo</h1>
        Title: <span text={id} >{id}</span>
        <img alt="sample" src={'data:image/png;base64, ' + imgData} />
    </>;
}

export default Photos;