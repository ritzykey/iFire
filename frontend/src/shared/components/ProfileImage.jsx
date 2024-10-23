import defaultProfileImage from '@/assets/defaultProfileImage.jpg';



export const ProfileImage = ({ width, tempImage, image }) => {
    
    const profileImage = image ? `/assets/profile/${image}` : defaultProfileImage;


    return <img className='rounded-circle shadow-sm mx-2'
        src={tempImage || profileImage}
        width={width}
        height={width}
        alt=""
        onError={({ target }) => {
            target.src = defaultProfileImage
        }}
    />;
}