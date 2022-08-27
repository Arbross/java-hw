import { useMemo } from 'react';
import { useDispatch } from 'react-redux';
import { bindActionCreators } from 'redux';
import  ActionCreators from '../components/store/action-creator';

export const useActions = () => {
    const dispatch = useDispatch();
    return useMemo(() => bindActionCreators(ActionCreators, dispatch), [dispatch]);
}