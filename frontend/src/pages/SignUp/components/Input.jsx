

const Input = (props) => {

    const { id, label, error, onChange, type='text' } = props;



    return <div className="mb-3">
        <label htmlFor={id} className="form-label">{label}</label>
        <input className={error ? "form-control is-invalid" : "form-control"}
            type={type} id={id}
            onChange={onChange} />
        <div className="invalid-feedback">
            {error}
        </div>
    </div>
}

export default Input;